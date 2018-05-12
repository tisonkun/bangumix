package com.wander4096.bangumix

import com.wander4096.bangumix.data.Anime
import com.wander4096.bangumix.service.AnimeRankService
import com.wander4096.bangumix.service.AnimeService
import com.wander4096.bangumix.service.UserService
import com.wander4096.bangumix.service.commentService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.servlet.ModelAndView
import org.springframework.web.servlet.mvc.support.RedirectAttributes
import org.springframework.web.servlet.view.RedirectView
import javax.servlet.http.HttpSession

@Controller
class Cowboy @Autowired constructor(
        private val userService: UserService,
        private val animeService: AnimeService,
        private val animeRankService: AnimeRankService,
        private val commentService: commentService,
        private val jdbcTemplate: JdbcTemplate
){
    @GetMapping("/")
    fun index(session: HttpSession, model: Model): String {
        model.addAttribute("animes", animeService.findAll())
        model.addAttribute("user", session.getAttribute("user"))
        return "index"
    }

    @GetMapping("/anime")
    fun anime(@RequestParam("name") name: String, model: Model): String {
        model.addAttribute("anime", animeService.findByName(name))
        model.addAttribute("comments", commentService.findAllByAnime(name))
        return "anime"
    }

    @GetMapping("login")
    fun login(): String {
        return "login"
    }

    @PostMapping("login")
    fun login(@RequestParam username: String,
              @RequestParam password: String,
              redirect: RedirectAttributes,
              session: HttpSession): String {
        if (userService.checkUsername(username) != null) {
            if (userService.checkUsernameAndPassword(username, password) != null) {
                session.setAttribute("user", username)
                return "redirect:/"
            } else {
                redirect.addFlashAttribute("errorMessage", "密码错误！")
                return "redirect:/login"
            }
        } else {
            redirect.addFlashAttribute("errorMessage", "用户不存在！")
            return "redirect:/login"
        }
    }

    @GetMapping("/logout")
    fun logout(session: HttpSession): String {
        session.removeAttribute("user")
        return "redirect:/"
    }

    @PostMapping("/add/anime")
    fun addAnime(@RequestParam animeName: String,
                 @RequestParam directorName: String,
                 @RequestParam synopsis: String): String {
        animeService.insertOne(Anime(animeName, directorName, synopsis))
        return "redirect:/"
    }
}