import React from 'react'
import { Switch, Route, Redirect } from 'react-router'

import Home from '../components/Pages/home/Home'
import UserCrud from '../Service/UserService/UserCrud'

export default props => 
    <Switch>
        <Route exact path='/' component={Home} />
        <Route path='/product' component={UserCrud} />
        <Redirect from='*' to='/' />
    </Switch>