import React, { useState, useEffect } from 'react';
import axios from 'axios';
import styled from 'styled-components';

const Container = styled.div`
  width: 40em;
  margin: 0 auto;
`;

const Title = styled.h2`
  text-align: center;
`;

const JsonCodeBlock = styled.code`
  background-color: #eee;
  border: 1px solid #999;
  display: block;
  padding: 20px;
`;

export const AllCakes: React.FC = () => {
  const [cakes, setCakes]: [string, (cakes: string) => void] = useState('');
  const [loading, setLoading]: [boolean, (loading: boolean) => void] = useState<boolean>(true);

  useEffect(() => {
    axios
      .get('http://localhost:8080/cakes')
      .then(response => {
        setCakes(JSON.stringify(response.data, null, 2));
        setLoading(false);
      });
  }, []);

  return (
    <Container>
      <Title>All Cakes JSON Data</Title>
      {loading ?
        (<div>Loading...</div>) :
        (<JsonCodeBlock>{cakes}</JsonCodeBlock>)}
    </Container>
  );
}
