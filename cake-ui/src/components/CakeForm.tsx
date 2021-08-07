import React from 'react';
import { Cake } from '../types/cake';
import { useCakeForm } from '../hooks/use-cake-form';
import styled from 'styled-components';
import { Error } from './Error';
import { Form, FormGroup, FormLabel, FormInput, FormSubmit } from './Form';

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