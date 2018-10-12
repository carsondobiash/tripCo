import React, {Component} from 'react'
import { Card, CardHeader, CardBody } from 'reactstrap'
import {ListGroup, ListGroupItem} from 'reactstrap'
import {Button, ButtonGroup} from 'reactstrap'

/* Options allows the user to change the parameters for planning
 * and rendering the trip map and itinerary.
 * The options reside in the parent object so they may be shared with the Trip object.
 * Allows the user to set the options used by the application via a set of buttons.
 */
class Customize extends Component{
    constructor(props) {
        super(props);
        this.state={current:"null"};
        this.reversePlaces = this.reversePlaces.bind(this);
    }


    reversePlaces(places){
        return places.reverse();
    }

    render() {

        let currentPlaces;
        let reverseButton;

        if(this.props.trip.places.length > 0){
            currentPlaces =
                <div>
                    <ListGroup>
                        {this.props.trip.places.map((place) =>
                            <ListGroupItem
                                tag="button"
                                id={place.name}
                                active={this.state.current === place.name}
                                onClick={((event) =>  this.setState({"current":event.target.innerText}))}
                            >
                                {place.name}
                            </ListGroupItem>
                        )}
                    </ListGroup>
                </div>;

            reverseButton =
                <div>
                    <Button
                        onClick={((event) => this.props.updatePlaces(this.reversePlaces(this.props.trip.places)))}
                    >{"Reverse"}
                    </Button>
                </div>
        }
        return(
            <Card>
                <CardBody>
                    {currentPlaces}
                    <ButtonGroup>
                    {reverseButton}
                    </ButtonGroup>
                </CardBody>
            </Card>
        )
    }
}

export default Customize;