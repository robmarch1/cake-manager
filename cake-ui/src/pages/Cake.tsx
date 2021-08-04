import React, { useState, useEffect } from 'react';
import { useParams } from "react-router-dom";
import axios from 'axios';
import styled from 'styled-components';

interface Cake {
  slug: string;
  title: string;
  description: string;
  image: string;
}
const defaultCake = { slug: '', title: '', description: '', image: '' };

type CakeParams = {
  slug: string;
};

const Container = styled.div`
  width: 40em;
  margin: 0 auto;
`;

export const Cake = () => {
  let { slug } = useParams<CakeParams>();
  const [cake, setCake]: [Cake | null, (cake: Cake | null) => void] = useState<Cake | null>(null);
  const [loading, setLoading]: [boolean, (loading: boolean) => void] = useState<boolean>(true);
  const [error, setError]: [string, (error: string) => void] = useState('');

  useEffect(() => {
      axios
        .get<Cake>(`http://localhost:8080/cakes/${slug}`)
        .then(response => {
          setCake(response.data);
          setLoading(false);
          setError('');
        }).catch((e) => {
          if (e.response?.status === 404) {
            setError('Cake not found');
          } else {
            setError('Something went wrong');
          }
        });
    }, []);

  return (
    <Container>
      {error !== '' ? (<div>{error}</div>) : null}
      {cake && (
        <>
          <h2>{cake.title}</h2>
          <p>{cake.description}</p>
          <i>{cake.image}</i>
        </>
      )}
    </Container>
  );
}
