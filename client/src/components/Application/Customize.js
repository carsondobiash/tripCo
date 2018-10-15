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
        this.deletePlace = this.deletePlace.bind(this);
        this.renderDeleteButton = this.renderDeleteButton.bind(this);
        this.renderReverseButton = this.renderReverseButton.bind(this);
        this.renderCurrentPlaces = this.renderCurrentPlaces.bind(this);
    }


    reversePlaces(places){
        return places.reverse();
    }

    deletePlace(placeToDelete) {
        let newIndex = 0;
        let afterRemove = [this.props.trip.places.length-1];
        for(let propIndex = 0; propIndex < (this.props.trip.places.length); propIndex++){
            if(!(this.props.trip.places[propIndex].name === placeToDelete)){
                afterRemove[newIndex] = this.props.trip.places[propIndex];
                newIndex++;
            }
        }
        return afterRemove;
    }

    renderDeleteButton(){
        let deleteButton =
            <Button
                onClick={((event) => this.props.updatePlaces(this.deletePlace(this.state.current)))}
            >{"Delete"}
            </Button>;

        return deleteButton;
    }

    renderReverseButton(){
        let reverseButton =
            <Button
                onClick={((event) => this.props.updatePlaces(this.reversePlaces(this.props.trip.places)))}
            >{"Reverse"}
            </Button>;

        return reverseButton;
    }

    renderCurrentPlaces(){
        let currentPlaces =
            <div>
                <ListGroup>
                    {this.props.trip.places.map((place) =>
                        <ListGroupItem
                            tag="button"
                            id={place.name}
                            active={this.state.current === place.name}
                            onClick={((event) => this.setState({"current": event.target.innerText}))}
                        >
                            {place.name}
                        </ListGroupItem>
                    )}
                </ListGroup>
            </div>;

        return currentPlaces;
    }





    render() {

        let currentPlaces;
        let reverseButton;
        let deleteButton;

        if(this.props.trip.places.length > 0) {
            currentPlaces = this.renderCurrentPlaces();
            reverseButton = this.renderReverseButton();
            deleteButton = this.renderDeleteButton();
        }
        return(
            <Card>
                <CardBody>
                    {currentPlaces}
                    <ButtonGroup>
                        {reverseButton}
                        {deleteButton}
                    </ButtonGroup>
                </CardBody>
            </Card>
        )
    }
}

export default Customize;