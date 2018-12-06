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
                </CardBody>
            </Card>
        )
    }
}

export default Bio;