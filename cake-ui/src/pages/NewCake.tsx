import React from 'react';
import styled from 'styled-components';
import { CakeForm } from '../components/CakeForm';

const Container = styled.div`
  width: 40em;
  margin: 0 auto;
`;

export const NewCake: React.FC = () => {
  return (
    <Container>
      <h2>Add new cake</h2>
      <CakeForm />
    </Container>
  );
};
