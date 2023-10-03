import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import {FooterComponent} from "./navigation/footer/footer.component";
import {SidenavComponent} from "./navigation/sidenav/sidenav.component";
import {ToolbarComponent} from "./navigation/toolbar/toolbar.component";


@NgModule({
  declarations: [
    AppComponent,
    FooterComponent,
    SidenavComponent,
    ToolbarComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
