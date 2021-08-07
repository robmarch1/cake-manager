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
       <h1>Cake Manager</h1>
     </Title>
     <Nav>
       <Link href='/'>Home</Link>
       <Link href='/cakes'>All Cakes</Link>
       <Link href='/new-cake'>New Cake</Link>
     </Nav>
   </HeaderContainer>
 );
}