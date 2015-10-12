// write more tests!
// https://rackt.github.io/redux/docs/recipes/WritingTests.html

import expect from 'expect';
import * as actions from '../src/actions';

describe('actions', () => {
  it('should create an action to modify moves', () => {
    const moves = 'mmoovveess';
    const expectedAction = {
      type: actions.MODIFY_MOVES,
      moves
    };
    expect(actions.modifyMoves(moves)).toEqual(expectedAction);
  });
});