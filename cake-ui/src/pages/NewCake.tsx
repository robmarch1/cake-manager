import React, { useState, useEffect } from 'react';
import { useParams } from "react-router-dom";
import axios from 'axios';
import styled from 'styled-components';
import { Cake } from '../types/cake';
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
