import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import {FooterComponent} from "./navigation/footer/footer.component";
import {SidenavComponent} from "./navigation/sidenav/sidenav.component";
import {ToolbarComponent} from "./navigation/toolbar/toolbar.component";
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {MatButtonModule} from "@angular/material/button";
import {MatSidenavModule} from "@angular/material/sidenav";
import {MatInputModule} from "@angular/material/input";
import {MatSelectModule} from "@angular/material/select";
import {CommonModule} from "@angular/common";
import {MatIconModule} from "@angular/material/icon";


@NgModule({
    declarations: [
        AppComponent,
        FooterComponent,
        SidenavComponent,
        ToolbarComponent
    ],
    imports: [
        BrowserModule,
        AppRoutingModule,
        BrowserAnimationsModule,
        MatButtonModule,
        MatSidenavModule,
        MatInputModule,
        MatSelectModule,
        CommonModule,
        MatIconModule
    ],
    providers: [],
    exports: [
        SidenavComponent
    ],
    bootstrap: [AppComponent]
})
export class AppModule { }
