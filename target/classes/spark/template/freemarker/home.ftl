<!DOCTYPE html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"></meta>
    <meta http-equiv="refresh" content="10">
    <title>${title} | Web Checkers</title>
    <link rel="stylesheet" type="text/css" href="/css/style.css">
</head>
<body>
  <div class="page">
  
    <h1>Web Checkers</h1>
    
    <div class="navigation">
        <#if currentPlayer??>
            <a href="/">my home</a> |
            <a href="/signout">sign out [${currentPlayer.name}]</a>
        <#else>
            <a href="/signin">sign in</a>
        </#if>
    </div>
    
    <div class="body">
      <p>Welcome to the world of online Checkers.</p>

      <#if message??>
        <div class="error">${message}</div>
      </#if>

      <#if result??>
        <div class="info">${result}</div>
      </#if>

      <p>Number of people playing: ${numPlayers}</p>

      <#if otherPlayers??>
        <form action="/game" method="POST">
          <#list otherPlayers as player>
            <input type="radio" name="name" value="${player.name}" checked>${player.name}<br>
          </#list>
            <input type="submit" value="Challenge">
        </form>
      </#if>

    </div>
    
  </div>
</body>
</html>
