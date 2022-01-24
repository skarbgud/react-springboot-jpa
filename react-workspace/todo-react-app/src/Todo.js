import React from 'react';
import {
  ListItem,
  ListItemText,
  InputBase,
  Checkbox,
  ListItemSecondaryAction,
  IconButton,
} from '@material-ui/core';
import { DeleteOutline } from '@material-ui/icons';

class Todo extends React.Component {
  constructor(props) {
    super(props);
    this.state = { item: props.item };
    this.delete = props.delete;
  }

  // 삭제에 대한 함수 작성
  deleteEvent = () => {
    this.delete(this.state.item);
  };

  render() {
    const item = this.state.item;
    return (
      <ListItem>
        <Checkbox checked={item.done} />
        <ListItemText>
          <InputBase
            inputProps={{ 'aria-label': 'naked' }}
            type="text"
            id={item.id}
            name={item.id}
            value={item.title}
            multiline={true}
            fullWidth={true}
          />
        </ListItemText>

        <ListItemSecondaryAction>
          <IconButton aria-label="Delete Todo" onClick={this.deleteEvent}>
            <DeleteOutline />
          </IconButton>
        </ListItemSecondaryAction>
      </ListItem>
    );
  }
}

export default Todo;
