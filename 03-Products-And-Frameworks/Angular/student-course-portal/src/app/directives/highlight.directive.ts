import { Directive, ElementRef, HostListener, Input, Renderer2 } from '@angular/core';

@Directive({
  selector: '[appHighlight]',
  standalone: true,
})
export class HighlightDirective {
  private highlightColor = '#fef08a';
  private resetColor = 'transparent';

  @Input()
  set appHighlight(value: string | null | undefined) {
    this.highlightColor = value?.trim() ? value : '#fef08a';
  }

  @Input()
  set defaultColor(value: string | null | undefined) {
    this.resetColor = value?.trim() ? value : 'transparent';
  }

  constructor(
    private readonly elementRef: ElementRef<HTMLElement>,
    private readonly renderer: Renderer2,
  ) {}

  @HostListener('mouseenter')
  onMouseEnter(): void {
    this.renderer.setStyle(
      this.elementRef.nativeElement,
      'background-color',
      this.highlightColor,
    );
  }

  @HostListener('mouseleave')
  onMouseLeave(): void {
    this.renderer.setStyle(
      this.elementRef.nativeElement,
      'background-color',
      this.resetColor,
    );
  }
}
