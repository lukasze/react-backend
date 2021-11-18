import React from 'react';
import { Button, Form } from 'semantic-ui-react';
import axios from 'axios';
import { useForm} from "react-hook-form";
import { ErrorMessage } from '@hookform/error-message';
import { useHistory } from 'react-router';
import SERVER_URL from '../../utils/constans';

function Create() {
  const { register, handleSubmit, formState: { errors }, getValues } = useForm();
  let history = useHistory();

  const callMockAPI = () => {
    
    const data = {
      firstName: getValues('firstName'),
      lastName: getValues('lastName')
    }
    console.log(data.firstName + " " + data.lastName);
    let endpointURL = `${SERVER_URL}/people`;
    axios.post(endpointURL, data)
          .then(() => history.push("/read"))
          .catch(err => console.log(err)); 
}

  return (
      <Form onSubmit={handleSubmit(callMockAPI)}>
        <Form.Field error={!!errors.firstName}>
          <label>First Name</label>
          <input placeholder='First Name'
            type="text"
            name="firstName"
            id="firstName"
            {...register("firstName", { required: "First Name is mandatory" })}
          />
          <ErrorMessage errors={errors} name="firstName" />
  
        </Form.Field>
        <Form.Field error={!!errors.lastName}>
          <label>Last Name</label>
          <input placeholder='Last Name'
            type="text"
            name="lastName"
            id="lastName"
            {...register("lastName", { required: "Last Name is mandatory" })}
          />
          <ErrorMessage errors={errors} name="lastName" />
        </Form.Field>
        <Form.Field><Button>Save</Button></Form.Field>
      </Form>
  );
}

export default Create;

