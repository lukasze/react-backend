import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import { Divider } from "semantic-ui-react";
import 'semantic-ui-css/semantic.min.css';
import HeaderMenu from "./components/HeaderMenu/HeaderMenu";
import Footer from "./components/Footer/Footer";
import Create from './components/Create/Create';
import Read from './components/Read/Read';
import Update from './components/Update/Update';
import './App.css';


function App() {

  const Home = () => <h1>Home</h1>;
  const MissingPage = () => <h1>URL doesn't exist</h1>;

  return (
    <Router>
        <HeaderMenu
          onItemClick={item => this.onItemClick(item)}
          items={[
            ["Home", "/"],
            ["Create", "/create"],
            ["Read", "/read"],
          ]}
          headerIcon={"compass outline"}
        />
      <Divider />
      <Divider />
          <Switch>
            <Route path="/" exact component={Home} />
            <Route path="/create" component={Create} />
            <Route path="/read" component={Read} />
            <Route path="/update" component={Update} />
            <Route component={MissingPage} />
          </Switch>
        <Footer />
    </Router>
  );
}

export default App;
