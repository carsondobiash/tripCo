import React, {Component} from 'react'
import {CardBody} from 'reactstrap'
import { Table } from 'reactstrap'

/* Options allows the user to change the parameters for planning
 * and rendering the trip map and itinerary.
 * The options reside in the parent object so they may be shared with the Trip object.
 * Allows the user to set the options used by the application via a set of buttons.
 */
class Itinerary extends Component{
    constructor(props) {
        super(props);

    }


    totalDistance(array){
        let total = [array[0]];

        array.map((distance, index) => {
                if (index !== 0) {
                    total[index] = total[index - 1] + distance
                }
            }
        );
        return total;
    }


    render() {
        return(
            <CardBody>
                <h4>{this.props.trip.title}</h4>
                <Table responsive bordered>
                    <thead>
                    </thead>
                    <tbody>
                        <tr>
                            <th scope='row'>Start</th>
                            {this.props.trip.places.length > 0 && this.props.trip.places.map(place =>
                                <td key={place.id}>{place.name}</td>
                            )}
                        </tr>
                        <tr>
                            <th scope='row'>Finish</th>
                            {this.props.trip.places.length > 0 && this.props.trip.places.map((place, index)=>
                                {
                                    if(index !== 0){
                                        return <td key={place.id}>{place.name}</td>
                                    }
                                }
                            )}
                            {this.props.trip.places.length > 0 && <td key={this.props.trip.places[0].id}>{this.props.trip.places[0].name}</td>}
                        </tr>
                        <tr>
                            <th scope='row'>Leg Distance</th>
                            {this.props.trip.distances && this.props.trip.distances.map((distance, index) =>
                                <td key={index}>{distance}</td>
                            )}

                        </tr>
                        <tr>
                            <th scope='row'>Total</th>
                            {this.props.trip.distances && this.totalDistance(this.props.trip.distances).map((num, index) =>
                                <td key={index}>{num}</td>
                            )}
                        </tr>
                    </tbody>
                </Table>
            </CardBody>

        )
    }
}

export default Itinerary;