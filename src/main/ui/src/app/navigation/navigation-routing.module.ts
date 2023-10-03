import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {FooterComponent} from "./footer/footer.component";
import {SidenavComponent} from "./sidenav/sidenav.component";
import {ToolbarComponent} from "./toolbar/toolbar.component";

const routes: Routes = [
  {
    path: 'footer',
    component: FooterComponent
  },
  {
    path: 'sidebar',
    component: SidenavComponent
  },
  {
    path: 'toolbar',
    component: ToolbarComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class NavigationRoutingModule { }
