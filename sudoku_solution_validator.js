/*
 * Tehtävänanto
 *
 * Toteuta funktio validSolution, joka tarkastaa, onko annettu sudokuratkaisu pätevä.
 * Sudoku annetaan taulukkona, jonka alkiot ovat taulukoita, jota edustavat jokaista sudokun riviä.
 *
 * Aikavaativuus
 *
 * Ohjelma käy läpi sudokuruudukon rivit ja sarakkeet muutaman kerran (käydessä laatikot läpi
 * käydään myös rivit ja sarakkeet läpi). Koska sudokuruudukko on aina vakiokokoinen, on
 * aikavaativuus väistämättä O(1).
 */
const BLOCK = {
  TOP_LEFT: 0,
  TOP_CENTER: 1,
  TOP_RIGHT: 2,
  MID_LEFT: 3,
  MID_CENTER: 4,
  MID_RIGHT: 5,
  BOTTOM_LEFT: 6,
  BOTTOM_CENTER: 7,
  BOTTOM_RIGHT: 8,
};

function getDigitMap() {
  const digitOccurences = new Map();
  for (let i = 1; i <= 9; i += 1) {
    digitOccurences.set(i, 0);
  }
  return digitOccurences;
}

function everyValueEqualsOne(array) {
  return array.every((value) => value === 1);
}

function containsAllDigitsOnce(subcollection) {
  const digitOccurences = getDigitMap();
  subcollection.forEach((digit) => {
    const currentOccurrences = digitOccurences.get(digit);
    digitOccurences.set(digit, currentOccurrences + 1);
  });

  const digitOccurenceValues = Array.from(digitOccurences.values());
  return everyValueEqualsOne(digitOccurenceValues);
}

function validateCollection(collection) {
  const array = Array.isArray(collection)
    ? collection
    : Array.from(collection.values());
  return array.every(containsAllDigitsOnce);
}

function collectRows(board) {
  const rows = [];
  for (let i = 0; i <= 8; i += 1) {
    rows.push(board[i]);
  }
  return rows;
}

function collectColumn(board, rowIndex) {
  const column = [];
  for (let columnIndex = 0; columnIndex <= 8; columnIndex += 1) {
    column.push(board[rowIndex][columnIndex]);
  }
  return column;
}

function collectColumns(board) {
  const columns = [];
  for (let rowIndex = 0; rowIndex <= 8; rowIndex += 1) {
    const column = collectColumn(board, rowIndex);
    columns.push(column);
  }
  return columns;
}

function locateBlock(rowIndex, columnIndex) {
  if (rowIndex <= 2 && columnIndex <= 2) return BLOCK.TOP_LEFT;
  if (rowIndex <= 2 && columnIndex <= 5) return BLOCK.TOP_CENTER;
  if (rowIndex <= 2 && columnIndex <= 8) return BLOCK.TOP_RIGHT;
  if (rowIndex <= 5 && columnIndex <= 2) return BLOCK.MID_LEFT;
  if (rowIndex <= 5 && columnIndex <= 5) return BLOCK.MID_CENTER;
  if (rowIndex <= 5 && columnIndex <= 8) return BLOCK.MID_RIGHT;
  if (rowIndex <= 8 && columnIndex <= 2) return BLOCK.BOTTOM_LEFT;
  if (rowIndex <= 8 && columnIndex <= 5) return BLOCK.BOTTOM_CENTER;
  if (rowIndex <= 8 && columnIndex <= 8) return BLOCK.BOTTOM_RIGHT;
  return null;
}

function pushValueToBlock(blockLocation, blocks, value) {
  const block = blocks.get(blockLocation);
  block.push(value);
  blocks.set(blockLocation, block);
}

function getBlockMap() {
  const blockMap = new Map();
  for (let i = 0; i <= 8; i += 1) {
    blockMap.set(i, []);
  }
  return blockMap;
}

function collectBlocks(board) {
  const blocks = getBlockMap();
  for (let row = 0; row <= 8; row += 1) {
    for (let column = 0; column <= 8; column += 1) {
      const blockLocation = locateBlock(row, column);
      const value = board[row][column];
      pushValueToBlock(blockLocation, blocks, value);
    }
  }
  return blocks;
}

/*
 * Tarkastaa, onko annettu sudokuratkaisu pätevä.
 * Palauttaa true, jos on, false, jos ei.
 */
function validSolution(board) {
  const rows = collectRows(board);
  const columns = collectColumns(board);
  const blocks = collectBlocks(board);
  return (
    validateCollection(rows) &&
    validateCollection(columns) &&
    validateCollection(blocks)
  );
}
