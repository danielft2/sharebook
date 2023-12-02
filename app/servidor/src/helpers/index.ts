export const optionalBooleanMapper = new Map([
  ['undefined', undefined],
  ['null', undefined],
  ['true', true],
  ['false', false],
  ['1', true],
  ['0', false],
  ['', true],
  [null, null],
  [undefined, undefined],
]);
