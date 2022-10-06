import '@vaadin/grid';
import { GridDataProviderCallback, GridDataProviderParams } from '@vaadin/grid/vaadin-grid';
import '@vaadin/grid/vaadin-grid-column';
import Person from 'Frontend/generated/com/example/application/data/entity/Person';
import * as PersonEndpoint from 'Frontend/generated/PersonEndpoint';
import { html } from 'lit';
import { customElement } from 'lit/decorators.js';
import { View } from '../view';

@customElement('people-view')
export class PeopleView extends View {
  render() {
    return html`
      <vaadin-grid class="w-full h-full" theme="no-border" .dataProvider=${this.dataProvider}>
        <vaadin-grid-column auto-width path="firstName"></vaadin-grid-column>
        <vaadin-grid-column auto-width path="lastName"></vaadin-grid-column>
        <vaadin-grid-column auto-width path="email"></vaadin-grid-column>
        <vaadin-grid-column auto-width path="phone"></vaadin-grid-column>
      </vaadin-grid>
    `;
  }

  async dataProvider(params: GridDataProviderParams<Person>, callback: GridDataProviderCallback<Person>) {
    const page = await PersonEndpoint.getPage(params.page, params.pageSize);
    if (page) callback(page.content, page.size);
  }

  async connectedCallback() {
    super.connectedCallback();
    this.classList.add('flex', 'flex-col', 'h-full');
  }
}
