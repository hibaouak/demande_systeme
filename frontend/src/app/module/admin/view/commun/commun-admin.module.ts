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

import { ActionDemandeCreateAdminComponent } from './action-demande/create/action-demande-create-admin.component';
import { ActionDemandeEditAdminComponent } from './action-demande/edit/action-demande-edit-admin.component';
import { ActionDemandeViewAdminComponent } from './action-demande/view/action-demande-view-admin.component';
import { ActionDemandeListAdminComponent } from './action-demande/list/action-demande-list-admin.component';
import { MotifDemandeCreateAdminComponent } from './motif-demande/create/motif-demande-create-admin.component';
import { MotifDemandeEditAdminComponent } from './motif-demande/edit/motif-demande-edit-admin.component';
import { MotifDemandeViewAdminComponent } from './motif-demande/view/motif-demande-view-admin.component';
import { MotifDemandeListAdminComponent } from './motif-demande/list/motif-demande-list-admin.component';

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

    ActionDemandeCreateAdminComponent,
    ActionDemandeListAdminComponent,
    ActionDemandeViewAdminComponent,
    ActionDemandeEditAdminComponent,
    MotifDemandeCreateAdminComponent,
    MotifDemandeListAdminComponent,
    MotifDemandeViewAdminComponent,
    MotifDemandeEditAdminComponent,
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
  ActionDemandeCreateAdminComponent,
  ActionDemandeListAdminComponent,
  ActionDemandeViewAdminComponent,
  ActionDemandeEditAdminComponent,
  MotifDemandeCreateAdminComponent,
  MotifDemandeListAdminComponent,
  MotifDemandeViewAdminComponent,
  MotifDemandeEditAdminComponent,
  ],
})
export class CommunAdminModule { }
