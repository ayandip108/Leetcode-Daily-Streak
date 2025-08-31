class Solution {
    public void solveSudoku(char[][] board) {
        solve(board, 0, 0);
    }
    private boolean solve(char[][] board, int iStart, int jStart){
        for(int i = iStart; i < 9; i++, jStart = 0){
            for(int j = jStart; j < 9; j++){
                if(board[i][j] != '.') continue;
                for(char p = '1'; p <= '9'; p++){
                    if(check(board, i, j, p)){
                        board[i][j] = p;
                        if(solve(board, i, j + 1)) return true;
                        else board[i][j] = '.';
                    }
                }
                return false;
            }
        }
        return true;
    }
    private boolean check(char[][] board, int row, int column, char num){
        for(int i = 0; i < 9; i++){
            if(num == board[i][column])  return false;
            if(num == board[row][i]) return false;
            if(num != '.' && board[3 * (row / 3) + i / 3][ 3 * (column / 3) + i % 3] == num)
                return false;
        }
        return true;
    }
}