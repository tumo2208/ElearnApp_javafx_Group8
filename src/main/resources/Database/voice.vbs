Dim Speaker


Set Speaker = CreateObject("SAPI.spVoice")
Set Speaker.Voice = Speaker.GetVoices.Item(1)
Speaker.Rate = 1
Speaker.Volume =70

Speaker.Speak "a la mode"