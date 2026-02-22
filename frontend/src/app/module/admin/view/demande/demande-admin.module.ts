import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import {ToastModule} from 'primeng/toast';
import {ToolbarModule} from 'primeng/toolbar';
import {TableModule} from 'primeng/table';
import {DropdownModule} from 'primeng/dropdown';
import {InputSwitchModule} from 'primeng/inputswitch';
import {TextareaModule} from 'primeng/textarea';
import {EditorModule} from "primeng/editor";

import { ConfirmDialogModule } from 'primeng/confirmdialog';
import { DialogModule } from 'primeng/dialog';
import {CalendarModule} from 'primeng/calendar';
import {PanelModule} from 'primeng/panel';
import {InputNumberModule} from 'primeng/inputnumber';
import {BadgeModule} from 'primeng/badge';
import { MultiSelectModule } from 'primeng/multiselect';
import {TranslateModule} from '@ngx-translate/core';
import {FileUploadModule} from 'primeng/fileupload';
import {FullCalendarModule} from '@fullcalendar/angular';
import {CardModule} from "primeng/card";
import {TagModule} from "primeng/tag";

import { DemandeCreateAdminComponent } from './demande/create/demande-create-admin.component';
import { DemandeEditAdminComponent } from './demande/edit/demande-edit-admin.component';
import { DemandeViewAdminComponent } from './demande/view/demande-view-admin.component';
import { DemandeListAdminComponent } from './demande/list/demande-list-admin.component';
import { ClientCreateAdminComponent } from './client/create/client-create-admin.component';
import { ClientEditAdminComponent } from './client/edit/client-edit-admin.component';
import { ClientViewAdminComponent } from './client/view/client-view-admin.component';
import { ClientListAdminComponent } from './client/list/client-list-admin.component';

import { PasswordModule } from 'primeng/password';
import { InputTextModule } from 'primeng/inputtext';
import {ButtonModule} from 'primeng/button';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {RouterModule} from '@angular/router';
import {TabViewModule} from 'primeng/tabview';
import { SplitButtonModule } from 'primeng/splitbutton';
import { MessageModule } from 'primeng/message';
import {MessagesModule} from 'primeng/messages';
import {PaginatorModule} from 'primeng/paginator';
import { IconField } from 'primeng/iconfield';
import { InputIcon } from 'primeng/inputicon';


@NgModule({
  declarations: [

    DemandeCreateAdminComponent,
    DemandeListAdminComponent,
    DemandeViewAdminComponent,
    DemandeEditAdminComponent,
    ClientCreateAdminComponent,
    ClientListAdminComponent,
    ClientViewAdminComponent,
    ClientEditAdminComponent,
  ],
  imports: [
    CommonModule,
    ToastModule,
    ToolbarModule,
    TableModule,
    ConfirmDialogModule,
    DialogModule,
    PasswordModule,
    InputTextModule,
    ButtonModule,
    FormsModule,
    ReactiveFormsModule,
    RouterModule,
    SplitButtonModule,
    DropdownModule,
    TabViewModule,
    InputSwitchModule,
    TextareaModule,
    CalendarModule,
    PanelModule,
    MessageModule,
    MessagesModule,
    InputNumberModule,
    BadgeModule,
    MultiSelectModule,
    PaginatorModule,
    TranslateModule,
    FileUploadModule,
    FullCalendarModule,
    CardModule,
    EditorModule,
    TagModule,
    IconField,
    InputIcon


  ],
  exports: [
  DemandeCreateAdminComponent,
  DemandeListAdminComponent,
  DemandeViewAdminComponent,
  DemandeEditAdminComponent,
  ClientCreateAdminComponent,
  ClientListAdminComponent,
  ClientViewAdminComponent,
  ClientEditAdminComponent,
  ],
})
export class DemandeAdminModule { }
