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
        this.makeFirst = this.makeFirst.bind(this);
        this.trickleSwap = this.trickleSwap.bind(this);
        this.renderDeleteButton = this.renderDeleteButton.bind(this);
        this.renderReverseButton = this.renderReverseButton.bind(this);
        this.renderCurrentPlaces = this.renderCurrentPlaces.bind(this);
        this.renderMakeFirstButton = this.renderMakeFirstButton.bind(this);
    }


    trickleSwap(stopIndex, places){
        let tempPlace;
        for(let afterIndex = 1; afterIndex < stopIndex; afterIndex++){
            tempPlace = places[afterIndex];
            places[afterIndex] = places[stopIndex];
            places[stopIndex] = tempPlace;
        }
        return places;
    }

    makeFirst(place){
        let updatedPlaces = this.props.trip.places;

        for (let index = 0; index < updatedPlaces.length; index++) {
            if (updatedPlaces[index].name === place) {
                place = updatedPlaces[0];
                updatedPlaces[0] = updatedPlaces[index];
                updatedPlaces[index] = place;
                this.trickleSwap(index, updatedPlaces);
            }
        }

        return updatedPlaces;
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

    renderMakeFirstButton(){
        let makeFirstButton =
            <Button
                onClick={((event) => this.props.updatePlaces(this.makeFirst(this.state.current)))}
            >{"Make First"}
            </Button>;

        return makeFirstButton;

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
        let makeFirstButton;

        if(this.props.trip.places.length > 0) {
            currentPlaces = this.renderCurrentPlaces();
            reverseButton = this.renderReverseButton();
            deleteButton = this.renderDeleteButton();
            makeFirstButton = this.renderMakeFirstButton();
        }
        return(
            <Card>
                <CardBody>
                    {currentPlaces}
                    <ButtonGroup>
                        {reverseButton}
                        {deleteButton}
                        {makeFirstButton}
                    </ButtonGroup>
                </CardBody>
            </Card>
        )
    }
}

export default Customize;