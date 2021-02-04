package AddressController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class AddressControllerEvents {
    //Initializing TextFields and assigning the number of characters each field can accept or contain
    final int NAME = 32;
    final int EMAIL = 32;
    final int PHONE = 12;
    final int STREET = 32;
    final int CITY = 20;
    final int STATE = 10;
    final int ZIP = 5;
    protected int count = 0;

    @FXML
    private TextField nameDisplay; //This displays the name
    @FXML
    private TextField emailDisplay; //This displays the mail address
    @FXML
    private TextField phoneDisplay; //This displays the phone contact
    @FXML
    private TextField streetDisplay; //This displays the Address
    @FXML
    private TextField cityDisplay; //This displays the City
    @FXML
    private TextField stateDisplay; //This displays the State
    @FXML
    private TextField zipDisplay; //This displays the Zip code
    @FXML
    private Label displayContent; //This shows the current address memory being read or written on run time

    /** This method writes or add an address to the file */
    public void onAddClicked(ActionEvent event) {
        try ( // Creating a random access file
              RandomAccessFile inout = new RandomAccessFile("AddressBook.dat", "rw");
        ) {
            inout.seek(inout.length());
            write(inout);
        }
        catch (FileNotFoundException ex) {
            displayContent.setText("File not Existing!");
        }
        catch (IOException ex) {}
        catch (IndexOutOfBoundsException ex) {
            displayContent.setText("Characters more than required!");
        }
    }

    /** This method fetches or reads an address from the file */
    public void onFirstClicked(ActionEvent event) {
        try ( // Creating a random access file
              RandomAccessFile inout = new RandomAccessFile("AddressBook.dat", "rw");
        ) {
            if (inout.length() > 0) {
                inout.seek(0);
                read(inout);
                displayContent.setText("Reading address #1");
                count = 1;
            }
            else {
                displayContent.setText("Address is empty!!");
            }
        }
        catch (IOException ex) {}
    }

    /** This method reads the next address of the file */
    public void onNextClicked(ActionEvent event) {
            try ( // Creating a random access file
                  RandomAccessFile inout = new RandomAccessFile("AddressBook.dat", "rw");
            ) {
                if (count * 143 < inout.length()) {
                    inout.seek(count * 143);
                    read(inout);
                    count++;
                    displayContent.setText("Reading address #" + count);
                }
                else {
                   displayContent.setText("End of file!");
                }
            }
            catch (IOException ex) {}
    }

    /** This method reads or fetches the content of the previous address of the file */
    public void onPreviousClicked(ActionEvent event) {
        try ( // Creating a random access file
              RandomAccessFile inout = new RandomAccessFile("AddressBook.dat", "rw");
        ) {
            if (count > 1)
                count--;
            else
                count = 1;
            inout.seek((count * 143) - 143);
            read(inout);
            displayContent.setText("Reading address #" + count);
        }
        catch (IOException ex) {}
    }

    /** This method reads the last address of the file */
    public void onLastClicked(ActionEvent event) {
        try ( // Creating a random access file
              RandomAccessFile inout = new RandomAccessFile("AddressBook.dat", "rw");
        ) {
            count = ((int)inout.length()) / 143;
            inout.seek((count * 143) - 143);
            read(inout);
            displayContent.setText("Reading address #" + count);
        }
        catch (IOException ex) {}
    }

    /** This method writes to the address and updates its content after modification of the file*/
    public void onUpdateClicked(ActionEvent event) {
        try ( // Creating a random access file
              RandomAccessFile inout = new RandomAccessFile("AddressBook.dat", "rw");
        ) {
            inout.seek(count * 143 - 143);
            write(inout);
        }
        catch (FileNotFoundException ex) {displayContent.setText("File does not exist!");}
        catch (IOException ex) {}
        catch (IndexOutOfBoundsException ex) {
            displayContent.setText("Characters more than required!");
        }
    }

    /** Method for writing to a file of fixedLength */
    private void write(RandomAccessFile inout) throws IOException {
        inout.write(fixedLength(nameDisplay.getText().getBytes(), NAME));
        inout.write(fixedLength(emailDisplay.getText().getBytes(), EMAIL));
        inout.write(fixedLength(phoneDisplay.getText().getBytes(), PHONE));
        inout.write(fixedLength(streetDisplay.getText().getBytes(), STREET));
        inout.write(fixedLength(cityDisplay.getText().getBytes(), CITY));
        inout.write(fixedLength(stateDisplay.getText().getBytes(), STATE));
        inout.write(fixedLength(zipDisplay.getText().getBytes(), ZIP));

        displayContent.setText("Address #" + count + " saved!");
    }

    /** Read address from file */
    private void read(RandomAccessFile inout) throws IOException {
        int pos; //position of the content of the file

        byte[] name = new byte[NAME];
        pos = inout.read(name);
        nameDisplay.setText(new String(name));

        byte[] email = new byte[EMAIL];
        pos += inout.read(email);
        emailDisplay.setText(new String(email));

        byte[] phone = new byte[PHONE];
        pos += inout.read(phone);
        phoneDisplay.setText(new String(phone));

        byte[] street = new byte[STREET];
        pos += inout.read(street);
        streetDisplay.setText(new String(street));

        byte[] city = new byte[CITY];
        pos += inout.read(city);
        cityDisplay.setText(new String(city));

        byte[] state = new byte[STATE];
        pos += inout.read(state);
        stateDisplay.setText(new String(state));

        byte[] zip = new byte[ZIP];
        pos += inout.read(zip);
        zipDisplay.setText(new String(zip));
    }

    /** Return an array of byte of fixed-length */
    private byte[] fixedLength(byte[] size, int n) {
        byte[] bytes = new byte[n];
        for (int i = 0; i < size.length; i++) {
            bytes[i] = size[i];
        }
        return bytes;
    }
}

