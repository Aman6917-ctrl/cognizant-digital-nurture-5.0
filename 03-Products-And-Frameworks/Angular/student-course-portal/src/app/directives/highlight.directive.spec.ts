import { Component, DebugElement } from '@angular/core';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { By } from '@angular/platform-browser';
import { Renderer2 } from '@angular/core';
import { HighlightDirective } from './highlight.directive';

@Component({
  standalone: true,
  imports: [HighlightDirective],
  template: `<div appHighlight data-testid="target">Hover me</div>`,
})
class HostComponent {}

describe('HighlightDirective', () => {
  let fixture: ComponentFixture<HostComponent>;
  let debugEl: DebugElement;
  let renderer: Renderer2;
  let directive: HighlightDirective;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [HostComponent],
    }).compileComponents();
    fixture = TestBed.createComponent(HostComponent);
    fixture.detectChanges();
    debugEl = fixture.debugElement.query(By.directive(HighlightDirective));
    directive = debugEl.injector.get(HighlightDirective);
    renderer = debugEl.injector.get(Renderer2);
  });

  it('should highlight on mouse enter and reset on mouse leave', () => {
    const setStyle = spyOn(renderer, 'setStyle').and.callThrough();

    directive.onMouseEnter();
    expect(setStyle).toHaveBeenCalledWith(
      debugEl.nativeElement,
      'background-color',
      '#fef08a',
    );

    directive.onMouseLeave();
    expect(setStyle).toHaveBeenCalledWith(
      debugEl.nativeElement,
      'background-color',
      'transparent',
    );
  });

  it('should react to host mouseenter and mouseleave events', () => {
    debugEl.triggerEventHandler('mouseenter', null);
    expect(debugEl.nativeElement.style.backgroundColor).toBe('rgb(254, 240, 138)');

    debugEl.triggerEventHandler('mouseleave', null);
    expect(debugEl.nativeElement.style.backgroundColor).toBe('transparent');
  });
});
