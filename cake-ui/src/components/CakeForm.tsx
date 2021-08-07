import React from 'react';
import { Cake } from '../types/cake';
import { useCakeForm } from '../hooks/use-cake-form';
import styled from 'styled-components';
import { Error } from './Error';

const Form = styled.form`
  margin: 1em 0;
  padding: 1em;
  border: solid 1px #1a1a1a;
  border-radius: 5px;
`;

const FormGroup = styled.div`
  width: 100%;
  margin: 0.5em 0;
  display: flex;
  justify-content: center;
`;

const FormLabel = styled.label`
  width: 30%;
  padding-right: 1em;
  text-align: right;
`;

const FormInput = styled.input`
  width: 50%;
`;

const FormSubmit = styled.input`
  padding: 0.5em;
  background-color: #284594;
  color: #fff;
  border: none;
  border-radius: 5px;
  text-align: center;
  width: 15em;
  cursor: pointer;

  &:hover {
    background-color: #3b63d1;
  }
`;

type CakeProps = {
  initialCake?: Cake;
}

export const CakeForm: React.FC<CakeProps> = (props) => {
  const initialState = props.initialCake || {
    slug: '',
    title: '',
    description: '',
    image: '',
  };

  const { onChange, onSubmit, cake, error } = useCakeForm(
    initialState
  );

  const storeLabel = !!props.initialCake ?
    'Update cake details' :
    'Store cake details';

  return (
    <Form onSubmit={onSubmit}>
      {error && (
        <Error>{error}</Error>
      )}
      <FormGroup>
        <FormLabel>Title:</FormLabel>
        <FormInput
          type="text"
          name="title"
          id="title"
          placeholder="Enter title"
          onChange={onChange}
          value={cake.title}
          required />
      </FormGroup>
      <FormGroup>
        <FormLabel>Description:</FormLabel>
        <FormInput
          type="text"
          name="description"
          id="description"
          placeholder="Enter description"
          onChange={onChange}
          value={cake.description}
          required />
      </FormGroup>
      <FormGroup>
        <FormLabel>Image URL:</FormLabel>
        <FormInput
          type="text"
          name="image"
          id="image"
          placeholder="Enter image URL"
          onChange={onChange}
          value={cake.image}
          required />
      </FormGroup>
      <FormGroup>
        <FormSubmit type="submit" value={storeLabel} />
      </FormGroup>
    </Form>
  );
};