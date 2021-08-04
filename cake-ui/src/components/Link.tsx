import React from 'react';
import styled from 'styled-components';

export const Link = styled.a`
  text-decoration: none;
  color: #1a1a1a;
  margin: 1em;
  transition: color 0.3em;

  &:hover {
    text-decoration: underline;
    color: #284594;
  }
`;

export const BorderedLink = styled(Link)`
  border: solid 1px #1a1a1a;
  border-radius: 5px;
  transition: none;

  &:hover {
    background-color: #284594;
    border-color: #284594;
    color: #fff;
    text-decoration: none;
  }
`;

export const InlineLink = styled(Link)`
    margin: 0;
`;
