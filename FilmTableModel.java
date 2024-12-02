import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class FilmTableModel extends AbstractTableModel {
    private List<Film> films = new ArrayList<>();
    private String[] columnNames = {"ID Film", "Judul", "Sutradara", "Rating", "Harga"};

    public void setFilms(List<Film> films) {
        this.films = films;
        fireTableDataChanged();
    }

    public Film getFilmAt(int row) {
        return films.get(row);
    }

    @Override
    public int getRowCount() {
        return films.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Film film = films.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return film.getId_film();
            case 1:
                return film.getJudul();
            case 2:
                return film.getSutradara();
            case 3:
                return film.getRating();
            case 4:
                return film.getHarga();
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }
}
