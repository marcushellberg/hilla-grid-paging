import { Route } from '@vaadin/router';
import './views/main-layout';
import './views/people/people-view';

export type ViewRoute = Route & {
  title?: string;
  icon?: string;
  children?: ViewRoute[];
};

export const views: ViewRoute[] = [
  // place routes below (more info https://vaadin.com/docs/latest/fusion/routing/overview)
  {
    path: '',
    component: 'people-view',
    icon: '',
    title: '',
  },
  {
    path: 'people',
    component: 'people-view',
    icon: 'la la-columns',
    title: 'People',
  },
];
export const routes: ViewRoute[] = [
  {
    path: '',
    component: 'main-layout',
    children: [...views],
  },
];
