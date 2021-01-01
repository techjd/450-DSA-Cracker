package com.techjd.a450dsacracker.ui.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import com.techjd.a450dsacracker.R
import org.w3c.dom.Text


class AboutFragment : Fragment() {
    private lateinit var toolbar: Toolbar
    private lateinit var toolbar_title: TextView
    private lateinit var privacy: TextView
    private lateinit var open: TextView
    private lateinit var github: TextView
    private lateinit var channel: TextView
    private lateinit var pdf: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_about, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        toolbar = view.findViewById(R.id.tl)
        toolbar_title = view.findViewById(R.id.ttl)
        toolbar_title.text = "About App ♥️"

        privacy = view.findViewById(R.id.privacy)
        open = view.findViewById(R.id.click)
        github = view.findViewById(R.id.git)
        channel = view.findViewById(R.id.channel)
        pdf = view.findViewById(R.id.pdf)


        privacy.setOnClickListener(View.OnClickListener {
            val defaultBrowser =
                Intent.makeMainSelectorActivity(Intent.ACTION_MAIN, Intent.CATEGORY_APP_BROWSER)
            defaultBrowser.data =
                Uri.parse("https://hopeful-edison-83f561.netlify.app/privacy-policy.html")
            startActivity(defaultBrowser)
        })

        open.setOnClickListener {
            val defaultBrowser =
                Intent.makeMainSelectorActivity(Intent.ACTION_MAIN, Intent.CATEGORY_APP_BROWSER)
            defaultBrowser.data =
                Uri.parse("https://play.google.com/store/apps/developer?id=Jaydeepsinh+Parmar")
            startActivity(defaultBrowser)
        }

        github.setOnClickListener {
            val defaultBrowser =
                Intent.makeMainSelectorActivity(Intent.ACTION_MAIN, Intent.CATEGORY_APP_BROWSER)
            defaultBrowser.data = Uri.parse("https://github.com/techjd/")
            startActivity(defaultBrowser)
        }

        channel.setOnClickListener {
            val defaultBrowser =
                Intent.makeMainSelectorActivity(Intent.ACTION_MAIN, Intent.CATEGORY_APP_BROWSER)
            defaultBrowser.data =
                Uri.parse("https://www.youtube.com/channel/UCQHLxxBFrbfdrk1jF0moTpw")
            startActivity(defaultBrowser)
        }

        pdf.setOnClickListener {
            val defaultBrowser =
                Intent.makeMainSelectorActivity(Intent.ACTION_MAIN, Intent.CATEGORY_APP_BROWSER)
            defaultBrowser.data =
                Uri.parse("https://drive.google.com/file/d/1FMdN_OCfOI0iAeDlqswCiC2DZzD4nPsb/view")
            startActivity(defaultBrowser)
        }
    }
}