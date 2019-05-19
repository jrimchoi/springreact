import React, { Component } from "react";
import ReactDOM from "react-dom";
import { Navigation } from "react-router";
import { Link } from "react-router-dom";
import axios from "axios";
import { Grid, GridColumn } from "@progress/kendo-react-grid";
import "@progress/kendo-theme-default/dist/all.css";

class App extends Component {
  constructor(props) {
    super(props);
    this.state = {
      contacts: []
    };
  }

  componentDidMount() {
    axios.get("/contacts").then(res => {
      this.setState({ contacts: res.data });
      console.log(this.state.contacts);
    });
  }
  OnRowSelected(sender, eventArgs) {
    var grid = sender;
    var MasterTable = grid.get_masterTableView();
    var row = MasterTable.get_dataItems()[
      eventArgs.get_itemIndexHierarchical()
    ];
    var cell = MasterTable.getCellByColumnUniqueName(row, "ContactName");
    alert(cell.innerHTML); //here cell.innerHTML holds the value of the cell
  }
  rowClick = event => {
    console.log(event.dataItem.id);
    //event.preventDefault();
    // this.transitionTo("/show/${event.dataItem.id}");
    // this.context.router.transitionTo("/show/${event.dataItem.id}");
    this.props.history.push("/show/" + event.dataItem.id);
  };
  render() {
    return (
      <div class="container">
        <div class="panel panel-default">
          <div class="panel-heading">
            <h3 class="panel-title">CONTACTS LIST</h3>
          </div>
          <div class="panel-body">
            <h4>
              <Link to="/create">
                <span
                  class="glyphicon glyphicon-plus-sign"
                  aria-hidden="true"
                />{" "}
                Add Contact
              </Link>
            </h4>
            <Grid data={this.state.contacts} onRowClick={this.rowClick}>
              <GridColumn field="name" title="Name" />
              <GridColumn field="address" />
            </Grid>
          </div>
        </div>
      </div>
    );
  }
}

export default App;
