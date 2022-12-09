/*
package project.myBoard;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import project.myBoard.categoryEnum.BoardCategory;
import project.myBoard.dto.AccountDto;
import project.myBoard.dto.PostDto;
import project.myBoard.entity.Account;
import project.myBoard.entity.Board;
import project.myBoard.entity.Post;
import project.myBoard.service.AccountService;
import project.myBoard.service.BoardService;
import project.myBoard.service.PostService;
import project.myBoard.service.ReplyService;



*/
/*3명의 유저(1,2,3)가 각 a,b,c board에 5개씩 post를 작성하고 각자 옆의 사람의 작성글에 reply를 달아줌*//*

@Component
@RequiredArgsConstructor
public class dbInit {



    @Component
    @Transactional
    @RequiredArgsConstructor
    class initService {
        private final AccountService accountService;
        private final BoardService boardService;
        private final PostService postService;
        private final ReplyService replyService;

        public void init1() {

        }


        public Account createAccount(String email, String username, String password) {
            AccountDto accountDto = new AccountDto();
            accountDto.setEmail(email);
            accountDto.setUsername(username);
            accountDto.setPassword(password);

            Account account = new Account().createAccount(accountDto);
            return account;
        }

        public Board createBoard() {
            Board board = new Board().createBoard(BoardCategory.A);
            return board;
        }

        public Post createPost() {
            PostDto postDto = new PostDto();
            postDto.set
        }
    }
}
*/
