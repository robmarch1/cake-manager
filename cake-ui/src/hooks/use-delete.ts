import { useState } from "react";
import axios from 'axios';
import { Cake } from '../types/cake';
import { useHistory } from "react-router-dom";
import { endpoint } from '../api';

export const useDelete = (cakeSlug: string) => {
  const [error, setError] = useState<string>();
  const history = useHistory();

  const onSubmit = async (event: React.FormEvent<HTMLFormElement>) => {
    event.preventDefault();
    axios
      .delete(endpoint(`/cakes/${cakeSlug}`))
      .then(response => {
        console.log('Redirecting...');
        history.push('/');
      })
      .catch((e) => {
        if (e.response?.status === 404) {
          setError('Cake not found');
        } else {
          setError('Something went wrong');
        }
      });
  };

  return {
    onSubmit,
    error
  };
};