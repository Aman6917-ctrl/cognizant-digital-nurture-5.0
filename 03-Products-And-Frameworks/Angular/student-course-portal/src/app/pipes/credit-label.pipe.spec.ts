import { CreditLabelPipe } from './credit-label.pipe';

describe('CreditLabelPipe', () => {
  const pipe = new CreditLabelPipe();

  it('should format single credit', () => {
    expect(pipe.transform(1)).toBe('1 Credit');
  });

  it('should format multiple credits', () => {
    expect(pipe.transform(3)).toBe('3 Credits');
  });

  it('should format advanced load credits', () => {
    expect(pipe.transform(4)).toBe('4 Credits (Advanced Load)');
  });

  it('should handle zero credits', () => {
    expect(pipe.transform(0)).toBe('No credits');
  });
});
