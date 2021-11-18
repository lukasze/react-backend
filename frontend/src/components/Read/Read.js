import React, { useEffect, useState } from 'react';
import { Button, Table } from 'semantic-ui-react';
import { Link } from 'react-router-dom';
import axios from 'axios';
import SERVER_URL from '../../utils/constans';

function Read() {
  const [tableData, setTableData] = useState([]);

  const callMockApiWithAxiosGET = () => {
      const endpointURL = `${SERVER_URL}/people`;
      axios.get(endpointURL)
        .then(response => setTableData(response.data));
    };

  function setLocalStorage(data) {
    console.log(data.id);
    localStorage.setItem("id", data.id);
    localStorage.setItem("firstName", data.firstName);
    localStorage.setItem("lastName", data.lastName);
  }

  
  const onDelete = (id) => {
    const endpointURL = `${SERVER_URL}/people/${id}`;
    axios.delete(endpointURL)
    .then(() => callMockApiWithAxiosGET())
    .catch(
      (err) => { console.log(err) }
      );
    }
    
  useEffect(() => {
    callMockApiWithAxiosGET();
  }, []);

  return (
    <div>
      <Table celled>
        <Table.Header>
          <Table.Row>
            <Table.HeaderCell>ID</Table.HeaderCell>
            <Table.HeaderCell>First Name</Table.HeaderCell>
            <Table.HeaderCell>Last Name</Table.HeaderCell>
            <Table.HeaderCell>Update</Table.HeaderCell>
            <Table.HeaderCell>Delete</Table.HeaderCell>
          </Table.Row>
        </Table.Header>

        <Table.Body>
          {
            tableData.map(data => {
              return (
                <Table.Row>
                  <Table.Cell>{data.id}</Table.Cell>
                  <Table.Cell>{data.firstName}</Table.Cell>
                  <Table.Cell>{data.lastName}</Table.Cell>
                  <Table.Cell>
                    <Link to="/update">
                      <Button color="green"
                        onClick={() => setLocalStorage(data)}>Update</Button>
                    </Link>
                  </Table.Cell>
                  <Table.Cell>
                    <Button color="red"
                            onClick={()=>onDelete(data.id)}>Delete</Button>
                  </Table.Cell>
                </Table.Row>
              )
            })
          }
        </Table.Body>
      </Table>
    </div>
  );
}

export default Read;
