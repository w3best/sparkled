import React from 'react';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import HomePage from './pages/Home/HomePage';
import EditSongPage from './pages/EditSong/EditSongPage';
import SchedulerPage from './pages/Scheduler/SchedulerPage';
import SongListPage from './pages/SongList/SongListPage';

const App = () => (
  <Router>
    <Switch>
      <Route exact path='/' component={HomePage}/>
      <Route exact path='/scheduler' component={SchedulerPage}/>
      <Route exact path='/songs' component={SongListPage}/>
      <Route exact path='/songs/:songId' component={EditSongPage}/>
    </Switch>
  </Router>
);

export default App;
