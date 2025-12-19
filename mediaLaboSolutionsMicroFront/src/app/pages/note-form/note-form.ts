import { Component, EventEmitter, Output } from '@angular/core'; 
import { CommonModule } from '@angular/common'; 
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-note-form',
  imports: [CommonModule, FormsModule],
  templateUrl: './note-form.html',
  styleUrl: './note-form.scss',
})
export class NoteForm {
  newNote : string = '';

  @Output() noteAdded = new EventEmitter<string>();

  addNote() : void {
    if (!this.newNote.trim()) return;

    this.noteAdded.emit(this.newNote);
    this.newNote = '';
  }
}
