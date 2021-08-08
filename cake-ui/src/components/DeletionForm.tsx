import React, { useState } from 'react';
import { useDelete } from '../hooks/use-delete';
import { Error } from './Error';
import { FormSubmit } from './Form';
import { Link } from './Link';

type DeletionProps = {
  slug: string;
}

export const DeletionForm: React.FC<DeletionProps> = (props) => {
  const { onSubmit, error } = useDelete(props.slug);
  const [ open, setOpen ] = useState<boolean>();

  function openForm(event: React.MouseEvent<HTMLElement>) {
    event.preventDefault();
    setOpen(true);
  };

  if (open) {
    return (
      <form onSubmit={onSubmit}>
        {error && (
          <Error>{error}</Error>
        )}
        <h3>Are you sure you want to delete this cake?</h3>
        <FormSubmit type="Submit" value="Yes" />
        <Link href={`/cakes/${props.slug}`}>No</Link>
      </form>
    );
  }
  return (
    <Link href="#" onClick={openForm}>Delete cake</Link>
  )
};
