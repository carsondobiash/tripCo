import React, {Component} from 'react'
import {Card, CardBody, Label, Input} from 'reactstrap'
import { ButtonGroup, Button, Form, FormGroup, Row, Col} from 'reactstrap'

/* Options allows the user to change the parameters for planning
 * and rendering the trip map and itinerary.
 * The options reside in the parent object so they may be shared with the Trip object.
 * Allows the user to set the options used by the application via a set of buttons.
 */
class Bio extends Component{
    constructor(props) {
        super(props);
    }

    render() {
        return(
            <Card>
                <CardBody>
                    <h4>Meet the team!</h4>
                    <div>
                        <h5>Carson Dobiash</h5>
                        <p>
                            {"I am a junior Computer Science student at CSU. I\'m interested in front\
                              end development so this was a very exciting project! I hope you enjoy the site!"}
                        </p>
                    </div>
                    <div>
                        <h5>Daniel Chavez</h5>
                        <img src="https://i.imgur.com/h2aemNK.jpg" width="175" height="175"/>
                        <p>
                            {"I am a computer science student at Colorado State University, currently in my junior year."}<br />
                            {"I am also an avid rockclimber who enjoys the outdoors."}
                        </p>
                    </div>
                    <div>
                        <h5>Jacob Augustine</h5>
                        <img src="https://render.bitstrips.com/v2/cpanel/73fbe124-efb6-42c1-8b3a-5c928dac974f-9e9857a1-1ac9-473c-be28-da5f4ed9785d-v1.png?transparent=1&palette=1" width="200" height="200"/>
                        <p>
                            {"Currently a junior at CSU majoring in Computer Science."} <br />
                            {"If I\'m not busy doing school work or at work. I\'m usually playing LoL."}
                        </p>
                    </div>
                    <div>
                        <h5>Brandon Jungen</h5>
                        <img src="https://i.imgur.com/UDgzs7B.jpg" width="175" height="175"/>
                        <p>
                            {"I am a Computer Science major in my third year"}
                            {"I enjoy video games and pickup sports when I can play a pick up game or two and more video games."}
                            {"Learning how to make this website was challenging but also fun at the same time, so enjoy it pelase."}
                        </p>
                    </div>
                </CardBody>
            </Card>
        )
    }
}

export default Bio;
