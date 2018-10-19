import './enzyme.config.js'                   // (1)
import React from 'react'
import { mount } from 'enzyme'              // (2)
import Customize from '../src/components/Application/Customize'
import Application from '../src/components/Application/Application'


/* Both of these tests are functionally identical although the standard way
 *  of writing tests uses lambda or anonymous functions. These are useful
 *  for defining functions that will only be in your code once but may be
 *  called multiple times by whatever they are passed to.
*/

/* A test response for our client to use;
 * this object represents the props that would be passed to the Options
 * component on construction.
 */
const startProps = {
    'trip': {
        "type":"trip",
        "title":"Colorado 14ers",
        "version":2,
        "options":{"units":"miles"},
        "places":
            [
                {
                    "name": "Mount Elbert",
                    "id": 1,
                    "Elevation": 14433,
                    "Estimated Prominence": 9093,
                    "latitude": 39.1177,
                    "longitude": -106.4453,
                    "Quadrangle": "Mount Elbert",
                    "Range": "Sawatch"
                },
                {
                    "name": "Mount Massive",
                    "id": 2,
                    "Elevation": 14421,
                    "Estimated Prominence": 1961,
                    "latitude": 39.1875,
                    "longitude": -106.4756,
                    "Quadrangle": "Mount Massive",
                    "Range": "Sawatch"
                },
            ]
    }
};


const custom = mount((   // (1)

    <Customize trip={startProps.trip} updatePlaces={(places) => startProps.trip.places = places}/>

));

let ListGroup = [];
let Buttons = [];
custom.find('ListGroupItem').map((element) => ListGroup.push(element));
custom.find('Button').map((element) => Buttons.push(element));

/* Test example using an anonymous function */
test('Check to see if current places get made correctly (Lambda)', () => {

    let actual = [];
    ListGroup.map((element) => actual.push(element.prop('id')));
    expect(actual).toEqual(["Mount Elbert","Mount Massive"]);
});

test('Check to see if current places groups click works (Lambda)', () => {

    ListGroup[0].simulate('click');
    expect(custom.state().current).toEqual("Mount Elbert");
});

test('Check to see if reversebutton onclick works (Lambda)', () => {
    Buttons[0].simulate('click');
    expect(startProps.trip.places[0].name).toEqual("Mount Massive");
});

test('Check to see if deletebutton onclick works (Lambda)', () => {

    Buttons[1].simulate('click');
    expect(startProps.trip.places.length).toEqual(1);
});

test('Check to see if MFbutton onclick works (Lambda)', () => {

    Buttons[2].simulate('click');
    expect(startProps.trip.places[0].name).toEqual("Mount Massive");
});