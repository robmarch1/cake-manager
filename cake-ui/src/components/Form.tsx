import styled from 'styled-components';

export const Form = styled.form`
  margin: 1em 0;
  padding: 1em;
  border: solid 1px #1a1a1a;
  border-radius: 5px;
`;

export const FormGroup = styled.div`
  width: 100%;
  margin: 0.5em 0;
  display: flex;
  justify-content: center;
`;

export const FormLabel = styled.label`
  width: 30%;
  padding-right: 1em;
  text-align: right;
`;

export const FormInput = styled.input`
  width: 50%;
`;

export const FormSubmit = styled.input`
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
