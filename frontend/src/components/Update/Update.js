import React, {useState, useEffect} from 'react';
import { Button, Form } from 'semantic-ui-react';
import { useHistory } from 'react-router';
import axios from 'axios';
import SERVER_URL from '../../utils/constans';

function Update() {
  const [firstName, setFirstName] = useState('');
  const [lastName, setLastName] = useState('');
  const [id, setId] = useState(null);
  let history = useHistory();


  useEffect(
    () => {
      setId(localStorage.getItem('id'));
      setFirstName(localStorage.getItem('firstName'));
      setLastName(localStorage.getItem('lastName'));
    },[]
  )


  const callMockAPI = () => {
      console.log(firstName + " " + lastName);

      const formData = {
        id,
        firstName,  
        lastName
      }

      const endpointURL = `${SERVER_URL}/people`;
      axios.put(endpointURL, formData)
            .then(()=> history.push("/read"))
            .catch(err => console.log(err)); 
  }
  
  
  return (
    <div>
      <Form>
        <Form.Field>
          <label>First Name</label>
          <input 
                 name="firstName" 
                 placeholder='First Name'
                 onChange={e=>setFirstName(e.target.value)}
                 value={firstName}
          />
        </Form.Field>
        <Form.Field>
          <label>Last Name</label>
          <input 
                 name="lastName"
                 placeholder='Last Name'
                 onChange={e=>setLastName(e.target.value)}
                 value={lastName}
          />
        </Form.Field>
        <Button 
              type='submit'
              onClick={callMockAPI}
        >Update</Button>
      </Form>
    </div>
  );
}

export default Update;

