import '@vaadin/vaadin-grid';
import { GridDataProviderCallback, GridDataProviderParams } from '@vaadin/vaadin-grid/vaadin-grid';
import '@vaadin/vaadin-grid/vaadin-grid-column';
import Person from 'Frontend/generated/com/example/application/data/entity/Person';
import * as PersonEndpoint from 'Frontend/generated/PersonEndpoint';
import { customElement, html, state } from 'lit-element';
import { View } from '../view';

@customElement('people-view')
export class PeopleView extends View {
  @state()
  people: Person[] = [];

  render() {
    return html`
      <vaadin-grid class="w-full h-full" theme="no-border" .items=${this.people}>
        <vaadin-grid-column auto-width path="firstName"></vaadin-grid-column>
        <vaadin-grid-column auto-width path="lastName"></vaadin-grid-column>
        <vaadin-grid-column auto-width path="email"></vaadin-grid-column>
        <vaadin-grid-column auto-width path="phone"></vaadin-grid-column>
      </vaadin-grid>
    `;
  }

  async connectedCallback() {
    super.connectedCallback();
    this.classList.add('flex', 'flex-col', 'h-full');
    this.people = await PersonEndpoint.listAll();
  }
}
