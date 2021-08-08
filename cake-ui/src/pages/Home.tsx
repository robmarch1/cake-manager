import React, { useState, useEffect } from 'react';
import axios from 'axios';
import styled from 'styled-components';
import { BorderedLink } from '../components/Link';
import { CakeSummary } from '../types/cake';
import { endpoint } from '../api';

const defaultCakeSummaries: CakeSummary[] = [];

const Container = styled.div`
  display: flex;
  flex-wrap: wrap;
  width: 20em;
  margin: 0 auto;
`;

const CakeLink = styled(BorderedLink)`
  width: 100%;
  padding: 1em;
`;

export const Home: React.FC = () => {
  const [cakes, setCakes]: [CakeSummary[], (cakes: CakeSummary[]) => void] = useState(defaultCakeSummaries);
  const [loading, setLoading]: [boolean, (loading: boolean) => void] = useState<boolean>(true);

  // Note: the empty deps array [] means
  // this useEffect will run once
  // similar to componentDidMount()
  useEffect(() => {
    axios
      .get<CakeSummary[]>(endpoint('/'))
      .then(response => {
        setCakes(response.data);
        setLoading(false);
      });
  }, []);

  if (loading) {
    return (
      <Container>Loading...</Container>
    );
  } else {
    return (
      <Container>
        {cakes.map((cake, i) => (
          <CakeLink href={'/cakes/' + cake.slug}>{cake.title}</CakeLink>
        ))}
      </Container>
    );
  }
}
