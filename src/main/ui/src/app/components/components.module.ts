import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ComponentsRoutingModule } from './components-routing.module';
import { DashboardComponent } from './dashboard/dashboard.component';


import {RegisterComponent} from "../auth/register/register.component";
import {FormComponent} from "./form/form.component";

@NgModule({
  declarations: [
    DashboardComponent,
    FormComponent,
    RegisterComponent,
  ],
  imports: [
    CommonModule,
    ComponentsRoutingModule
  ]
})
export class ComponentsModule { }
