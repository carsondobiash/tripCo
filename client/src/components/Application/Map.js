import React, {Component} from 'react'
import {Card, CardHeader, CardBody, CardImg, Navbar, NavbarBrand, NavbarToggler, Col, Row, Collapse} from 'reactstrap'
import { ButtonGroup, Button, Form } from 'reactstrap'

/* Options allows the user to change the parameters for planning
 * and rendering the trip map and itinerary.
 * The options reside in the parent object so they may be shared with the Trip object.
 * Allows the user to set the options used by the application via a set of buttons.
 */
class Map extends Component{
    constructor(props) {
        super(props);
        this.state = {
            collapse: false
        }
        this.saveMap = this.saveMap.bind(this);
        this.toggleNav = this.toggleNav.bind(this);
    }

    saveMap(e){
        e.preventDefault(event);
        let map =("map." + event.target.name);
        let downloader = document.createElement("a");
        let file = new Blob([this.props.trip.map], {type: event.target.name});
        downloader.href = URL.createObjectURL(file);
        downloader.download = map;
        downloader.click();
    }

    toggleNav(){
        this.setState({collapse: !this.state.collapse})
    }

    render() {
        return(
            <Navbar light>
                <NavbarBrand>Map</NavbarBrand>
                <NavbarToggler onClick={this.toggleNav}/>
                <Collapse isOpen={this.state.collapse}>
                <CardBody>
                <CardImg src={"data:image/svg+xml;utf8," + this.props.trip.map}/>
                <Button type="submit" name="svg" onClick={this.saveMap}>
                    Download
                </Button>
                </CardBody>
                </Collapse>
            </Navbar>
        )
    }
}

export default Map;