package io.malachai.homebar.domain;

import java.util.Optional;

public interface TokenReader {

    Optional<String> read(String header);
}
