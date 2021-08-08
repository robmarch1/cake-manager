import { useState } from "react";
import axios from 'axios';
import { Cake } from '../types/cake';
import { useHistory } from "react-router-dom";
import { endpoint } from '../api';

export const useCakeForm = (initialState: Cake) => {
  const [cake, setCake] = useState<Cake>(initialState);
  const [error, setError] = useState<string>();
  const history = useHistory();

  // onChange
  const onChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    setCake({ ...cake, [event.target.name]: event.target.value });
  };

  // onSubmit
  const onSubmit = async (event: React.FormEvent<HTMLFormElement>) => {
    event.preventDefault();
    if (!cake.title) {
      setError('Enter a title');
      return;
    }
    if (!cake.description) {
      setError('Enter a description');
      return;
    }
    if (!cake.image) {
      setError('Enter an image URL');
      return;
    }
    if (!cake.slug || cake.slug.trim() === '') {
      console.log('Updating slug to ' + cake.title.trim().toLowerCase().replaceAll(' ', '-'));
      cake.slug = cake.title.trim().toLowerCase().replaceAll(' ', '-');
    } else {
      console.log('Not updating slug');
    }
    console.log('Cake: [' + JSON.stringify(cake) + ']');
    axios
      .put(endpoint('/cakes'), cake, {
        headers: {
          "Access-Control-Allow-Origin": "*",
          'content-type': 'application/json'
        }
      })
      .then(response => {
        console.log('Redirecting...');
        history.push('/cakes/' + cake.slug);
      })
      .catch((e) => {
        if (e.response?.status === 422) {
          setError('Invalid cake');
        } else if (e.response?.status === 304) {
          setError('No cake data modified');
        } else {
          setError('Something went wrong');
        }
      });
  };

  // return values
  return {
    onChange,
    onSubmit,
    cake,
    error,
  };
}