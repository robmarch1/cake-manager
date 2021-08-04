import React from 'react';
import styled from 'styled-components';
import { Link } from './Link';

const HeaderContainer = styled.div`
  display: flex;
  flex-wrap: wrap
`;

const Title = styled.div`
  width: 100%;
  text-align: center;
`;

const Nav = styled.div`
  width: 100%;
  display: flex;
  justify-content: center;
`;

export const Header: React.FC = () => {
 return (
   <HeaderContainer>
     <Title>
       <h1>Hello World!</h1>
     </Title>
     <Nav>
       <Link href='/'>Home</Link>
       <Link href='/cakes'>All Cakes</Link>
     </Nav>
   </HeaderContainer>
 );
}