import React from 'react';
import { Link } from 'react-router-dom';
import { Segment, Container, Icon } from "semantic-ui-react";

const Footer = () => (
  <Segment inverted>
    <Container textAlign="center">
          An example from codesandbox.io mixed with live coding
      <Icon name="react" style={{ marginLeft: "5px" }} />
    </Container>
  </Segment>
);

export default Footer;
