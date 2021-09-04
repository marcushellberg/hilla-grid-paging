import '@vaadin/vaadin-grid';
import { GridDataProviderCallback, GridDataProviderParams } from '@vaadin/vaadin-grid/vaadin-grid';
import '@vaadin/vaadin-grid/vaadin-grid-column';
import * as PersonEndpoint from 'Frontend/generated/PersonEndpoint';
import { customElement, html, state } from 'lit-element';
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

  async dataProvider(params: GridDataProviderParams, callback: GridDataProviderCallback) {
    const page = await PersonEndpoint.getPage(params.page, params.pageSize);

    callback(page.content, page.size);
  }

  async connectedCallback() {
    super.connectedCallback();
    this.classList.add('flex', 'flex-col', 'h-full');
  }
}
